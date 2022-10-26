class NotImplementedException(s: String) extends RuntimeException(s)
class ParseException(s: String) extends RuntimeException(s)
//class ReadException(s: String) extends ParseException(s)
class InterpretException(s: String) extends RuntimeException(s)
class ExplainException(s: String) extends RuntimeException(s)

//SExpr - Symbolic expression.
//abstract class SExpr
//case class SNum(n: Int) extends SExpr
//case class SSym(s:String) extends SExpr
//case class SList(list: List[SExpr]) extends SExpr

trait AST
type DomId = String
type Tagged = (Elem, DomId)
type Arguments = Either[List[Term], List[Modifier]]

case class Var(dom: DomId, s: String) extends AST

abstract class Elem extends AST
case class Str(s: String) extends Elem
case class Integer(n: Int) extends Elem
case class Product(taggeds: List[Tagged]) extends Elem

abstract class Domain extends AST
case class AnyString() extends Domain
case class AnyInt() extends Domain
case class Strings(strings: List[String]) extends Domain
case class Ints(ints: List[Int]) extends Domain
case class Products(vars: List[Var]) extends Domain
case class Time() extends Domain

abstract class Modifier extends AST
case class Rename(v: Var, t: Term) extends Modifier

abstract class Kind extends AST
case class Fact(f: FactSpec) extends Kind
case class Act(a: ActSpec) extends Kind
case class Duty(d: DutySpec) extends Kind
case class Event(e: EventSpec) extends Kind

case class Restriction(v: Var) extends AST
//abstract class Restriction extends AST
//case class VarRestriction(v: Var) extends Restriction
//case class FunctionRestriction(f: ) extends Restriction

abstract class Derivation extends AST
case class Dv(vars: List[Var], t: Term) extends Derivation
case class HoldsWhen(t: Term) extends Derivation

case class TypeSpec(kind: Kind, domain: Domain, domain_constraint: Term, restriction: /*Maybe */ Restriction, derivation: List[Derivation], closed: Boolean, conditions: List[Term]) extends AST
case class DutySpec(enforcingActs: List[DomId], terminatingActs: List[DomId], creatingActs: List[DomId], violatedWhen: List[Term]) extends AST
case class FactSpec(invariant: Boolean, actor: Boolean) extends AST
case class ActSpec(effects: List[Effect], syncs: List[Sync], physical: Boolean) extends AST
case class EventSpec(event_effects: List[Effect], event_syncs: List[Sync]) extends AST
case class Spec(decls: Set[(DomId, TypeSpec)], aliases: Set[(DomId, DomId)]) extends AST

abstract class Effect extends AST
case class CAll(vars: List[Var], t:Term) extends Effect
case class TAll(vars: List[Var], t:Term) extends Effect
case class OAll(vars: List[Var], t:Term) extends Effect

case class Sync(vars: List[Var], t:Term) extends AST

type Initializer = List[Effect]

//case class Statement = trans monad??

//abstract class TransType
//case class Trigger() extends TransType
//case class AddEvent() extends TransType
//case class RemEvent() extends TransType
//case class ObfEvent() extends TransType

//type Scenario = List[Statement]

abstract class Phrase extends AST
case class PDo(t: Tagged) extends Phrase
case class PTrigger(vars: List[Var], t: Term) extends Phrase
case class Create(vars: List[Var], t: Term) extends Phrase
case class Terminate(vars: List[Var], t: Term) extends Phrase
case class Obfuscate(vars:List[Var], t: Term) extends Phrase
case class Query(t: Term) extends Phrase
case class PInstQuery(vars: List[Var], t: Term) extends Phrase
case class PDeclBlock(decls: List[Decl]) extends Phrase
case class PSkip() extends Phrase
//case class Seq(p1: Phrase, p2: Phrase) extends Phrase

abstract class Decl extends AST
case class TypeDecl(d:DomId, ts:TypeSpec) extends Decl
case class TypeExt(d:DomId, mc:List[ModClause]) extends Decl
case class PlaceholderDecl(d1:DomId, d2:DomId) extends Decl

abstract class ModClause extends AST
case class ConditionedByCl(ts:List[Term]) extends ModClause
case class DerivationCl(ts:List[Term]) extends ModClause
case class PostCondCl(ts:List[Term]) extends ModClause
case class SyncCl(ts:List[Term]) extends ModClause
case class ViolationCl(ts:List[Term]) extends ModClause
case class EnforcingActsCl(ts:List[Term]) extends ModClause
case class TerminatedByCl(ts:List[Term]) extends ModClause
case class CreatedByCl(ts:List[Term]) extends ModClause

type Subs = List[(Var, Tagged)]

abstract class Term extends AST
case class Not(t: Term) extends Term
case class Present(t:Term) extends Term
case class Violated(t: Term) extends Term
case class Enabled(t: Term) extends Term
case class BoolLit(b: Boolean) extends Term
case class StringLit(s: String) extends Term
case class IntLit(n: Int) extends Term
case class Project(t: Term, v: String) extends Term
case class And(t1: Term, t2: Term) extends Term
case class Or(t1: Term, t2: Term) extends Term
case class Leq(t1: Term, t2: Term) extends Term
case class Geq(t1: Term, t2: Term) extends Term
case class G(t1: Term, t2: Term) extends Term
case class L(t1: Term, t2: Term) extends Term
case class Eq(t1: Term, t2: Term) extends Term
case class Neq(t1: Term, t2: Term) extends Term
case class When(t1: Term, t2: Term) extends Term
case class Sub(t1: Term, t2: Term) extends Term
case class Add(t1: Term, t2: Term) extends Term
case class Mult(t1: Term, t2: Term) extends Term
case class Mod(t1: Term, t2: Term) extends Term
case class Div(t1: Term, t2: Term) extends Term
case class Exists(vars: List[String], t: Term) extends Term
case class Forall(vars: List[String], t: Term) extends Term
case class Count(vars: List[String], t: Term) extends Term
case class Sum(vars: List[String], t: Term) extends Term
case class Max(vars: List[String], t: Term) extends Term
case class Min(vars: List[String], t: Term) extends Term

abstract class Value
case class ResNum(i:Int) extends Value
case class ResBool(b:Boolean) extends Value
case class ResString(s: String) extends Value
case class ResTagged(t: Tagged) extends Value

sealed abstract class Type
case class TyStrings() extends Type
case class TyInts() extends Type
case class TyBool() extends Type
case class TyTagged(t:Tagged) extends Type

object AST {
  val keywords: Set[String] = Set("!?", "||", "&&", "<=", ">=", "..", "True", "False", "Sum", "==", "!=", "When", "Where", "Holds when", "Holds", "Present",
                     "Present when", "Max", "Min", "Count", "Union", "Enabled", "Violated when", "Violated", "Atom", "String", "Int", "Time",
                     "Current Time", "Exists", "Forall", "Foreach", "Force", "Extend", "Event", "Act", "Fact", "Physical", "Bool", "Var",
                     "Function", "Invariant", "Predicate", "Duty", "Actor", "Holder", "Claimant", "Recipient", "Related to", "Conditioned by",
                     "Creates", "Terminates", "Obfuscates", "Terminated by", "Created by", "With", "Identified by", "Derived from",
                     "Derived externally", "Enforced by"  , "Syncs with", "Do"  , "Placeholder", "For", "Not", "Open", "Closed", "?-", "#", "##",
                     "###", "####", "#include", "#require")
}

//object parse {
//  def parse(s: SExpr): AST = s match {
//    case SNum(n) => IntLit(n)
//    case SSym("True") => BoolLit(true)
//    case SSym("False") => BoolLit(false)
//    case SSym(x) if AST.keywords.contains(x) => throw new ParseException(x +  " cannot be used as an identifier")
//    case SSym(x) => Var(x, "TODO: idk what to put here")
//    case SList(a1 :: SSym(binop) :: a2 :: Nil) =>
//      val l = parse(a1)
//      val r = parse(a2)
//      val test = IntLit(1)
//      if (l.getClass != test.getClass) throw new ParseException("Not a term: " + a1)
//      if (r.getClass != test.getClass) throw new ParseException("Not a term: " + a2)
//      val lTerm = l.asInstanceOf[Term]
//      val rTerm = l.asInstanceOf[Term]
//      binop match {
//        case "When" => When(lTerm, rTerm)
//        case "||" => Or(lTerm, rTerm)
//        case "&&" => And(lTerm, rTerm)
//        case "==" => Eq(lTerm, rTerm)
//        case "!=" => Neq(lTerm, rTerm)
//        case ">=" => Geq(lTerm, rTerm)
//        case "<=" => Leq(lTerm, rTerm)
//        case "<" => L(lTerm, rTerm)
//        case ">" => G(lTerm, rTerm)
//        case "-" => Sub(lTerm, rTerm)
//        case "+" => Add(lTerm, rTerm)
//        case "*" => Mult(lTerm, rTerm)
//        case "%" => Mod(lTerm, rTerm)
//        case "/" => Div(lTerm, rTerm)
//        case _ => throw new ParseException("unrecognized keyword for binary operation " + binop)
//      }
//    case SList(SSym(unop) :: a :: Nil) =>
//      val e = parse(a)
//      val test = IntLit(1)
//      if (e.getClass != test.getClass) throw new ParseException("Not a Term: " + a)
//      val eTerm = e.asInstanceOf[Term]
//      unop match {
//        case "!" => Not(eTerm)
//        case "Not" => Not(eTerm)
//        case "Present" => Present(eTerm)
//        case "Violated" => Violated(eTerm)
//        case "Enabled" => Enabled(eTerm)
//      }
//
//    case _ => throw new ParseException("cannot parse " + s)
//  }
//}

object interpret {
  def interp(a: AST): Value = a match {


    case _ => throw new InterpretException("cannot interpret " + a)
  }
}

object Explain {
  /*
    Enter phrase (for now, only consider queries concerning whether duties exist). If the duty exists in the knowledge
    base, search in the trace at what step it was created and by what act and incorporate that in the end result. Using
    the spec, list conditions (search specifically for the holds when keyword in the spec). Search for this condition in
    the knowledge base, then determine again in the trace where it is attributed a value. Use these pieces for now to give
    an explanation.

    example result:
    #7 > ?Duty-to-pay(Alice, VD, Sweater, 30)
    query successful:
      Act order-product creates Duty duty-to-send when in-stock is True and when time-in-minutes < 30.
      At #6 order-product(Alice, VD, Sweater)
      At #5 +in-stock()
      At #4 +time-in-minutes(0)
  */
  def explain(p: Phrase, s: List[Spec], Trace: List[String], KnowledgeBase: List[Term]): List[Any] = p match {
    case query(term) if KnowledgeBase.contains(term) => throw new NotImplementedException("TODO")

    case _ => throw new ExplainException("cannot explain" + p)
  }

  // def prettify(res: List[Any]): String = res match {
  //   case _ => throw new NotImplementedException("TODO")
  // }
}