import Types.{Arguments, DomId, Tagged}

trait AST
case class Var(dom: DomId, s: String) extends AST
case class Sync(vars: List[Var], t: Term) extends AST

object AST {
  val keywords: Set[String] = Set("!?", "||", "&&", "<=", ">=", "..", "True", "False", "Sum", "==", "!=", "When",
    "Where", "Holds when", "Holds", "Present", "Present when", "Max", "Min", "Count", "Union",
    "Enabled", "Violated when", "Violated", "Atom", "String", "Int", "Time", "Current Time", "Exists",
    "Forall", "Foreach", "Force", "Extend", "Event", "Act", "Fact", "Physical", "Bool", "Var",
    "Function", "Invariant", "Predicate", "Duty", "Actor", "Holder", "Claimant", "Recipient",
    "Related to", "Conditioned by", "Creates", "Terminates", "Obfuscates", "Terminated by",
    "Created by", "With", "Identified by", "Derived from", "Derived externally", "Enforced by",
    "Syncs with", "Do"  , "Placeholder", "For", "Not", "Open", "Closed", "?-", "#", "##", "###",
    "####", "#include", "#require")
}

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

abstract class Restriction extends AST
case class VarRestriction() extends Restriction
case class FunctionRestriction() extends Restriction

abstract class Derivation extends AST
case class Dv(vars: List[Var], t: Term) extends Derivation
case class HoldsWhen(t: Term) extends Derivation

case class TypeSpec(kind: Kind, domain: Domain, domain_constraint: Term,
                    restriction: /*Maybe */ Restriction, derivation: List[Derivation],
                    closed: Boolean, conditions: List[Term]) extends AST
case class DutySpec(enforcingActs: List[DomId], terminatingActs: List[DomId],
                    creatingActs: List[DomId], violatedWhen: List[Term]) extends AST
case class FactSpec(invariant: Boolean, actor: Boolean) extends AST
case class ActSpec(effects: List[Effect], syncs: List[Sync], physical: Boolean) extends AST
case class EventSpec(event_effects: List[Effect], event_syncs: List[Sync]) extends AST
case class Spec(decls: Set[(DomId, TypeSpec)], aliases: Set[(DomId, DomId)]) extends AST

abstract class Effect extends AST
case class CAll(vars: List[Var], t: Term) extends Effect
case class TAll(vars: List[Var], t: Term) extends Effect
case class OAll(vars: List[Var], t: Term) extends Effect

abstract class Statement extends AST
case class Trans(vars: Var, ty: Either[Term, (DomId, Arguments)]) extends Statement
case class Query(t: Term) extends Statement

abstract class TransType extends AST
case class Trigger() extends TransType
case class AddEvent() extends TransType
case class RemEvent() extends TransType
case class ObfEvent() extends TransType

abstract class Phrase extends AST
case class PDo(t: Tagged) extends Phrase
case class PTrigger(vars: List[Var], t: Term) extends Phrase
case class Create(vars: List[Var], t: Term) extends Phrase
case class Terminate(vars: List[Var], t: Term) extends Phrase
case class Obfuscate(vars: List[Var], t: Term) extends Phrase
case class PQuery(t: Term) extends Phrase
case class PInstQuery(vars: List[Var], t: Term) extends Phrase
case class PDeclBlock(decls: List[Decl]) extends Phrase
case class PSkip() extends Phrase
//case class Seq(p1: Phrase, p2: Phrase) extends Phrase

abstract class Decl extends AST
case class TypeDecl(d: DomId, ts: TypeSpec) extends Decl
case class TypeExt(d: DomId, mc: List[ModClause]) extends Decl
case class PlaceholderDecl(d1: DomId, d2: DomId) extends Decl

abstract class ModClause extends AST
case class ConditionedByCl(ts: List[Term]) extends ModClause
case class DerivationCl(ts: List[Term]) extends ModClause
case class PostCondCl(ts: List[Term]) extends ModClause
case class SyncCl(ts: List[Term]) extends ModClause
case class ViolationCl(ts: List[Term]) extends ModClause
case class EnforcingActsCl(ts: List[Term]) extends ModClause
case class TerminatedByCl(ts: List[Term]) extends ModClause
case class CreatedByCl(ts: List[Term]) extends ModClause

abstract class Term extends AST
case class Not(t: Term) extends Term
case class Present(t: Term) extends Term
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
case class ResNum(i: Int) extends Value
case class ResBool(b: Boolean) extends Value
case class ResString(s: String) extends Value
case class ResTagged(t: Tagged) extends Value

sealed abstract class Type
case class TyStrings() extends Type
case class TyInts() extends Type
case class TyBool() extends Type
case class TyTagged(t:Tagged) extends Type