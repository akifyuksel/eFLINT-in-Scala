import Types.{Arguments, DomId, Tagged}

class NotImplementedException(s: String) extends RuntimeException(s)
//class ParseException(s: String) extends RuntimeException(s)
//class ReadException(s: String) extends ParseException(s)
class InterpException(s: String) extends RuntimeException(s)
class ExplainException(s: String) extends RuntimeException(s)

//SExpr - Symbolic expression.
//abstract class SExpr
//case class SNum(n: Int) extends SExpr
//case class SSym(s:String) extends SExpr
//case class SList(list: List[SExpr]) extends SExpr

//object parse {
//  def parse(s: SExpr): AST = s match {
//    case SNum(n) => IntLit(n)
//    case SSym("True") => BoolLit(true)
//    case SSym("False") => BoolLit(false)
//    case SSym(x) if AST.keywords.contains(x) => throw new ParseException(x +  " cannot be used as an identifier")
//    case SSym(x) => Var(x, "idk what to put here")
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

object Interp {
  type Store = List[Var]
  def interp(a: AST, st: Store): (Value, AST, Store) = a match {
    case Str(s) => throw new NotImplementedException("not yet implemented")
    case Integer(n) => throw new NotImplementedException("not yet implemented")
    case Product(taggeds) => throw new NotImplementedException("not yet implemented")

    case AnyString() => throw new NotImplementedException("not yet implemented")
    case AnyInt() => throw new NotImplementedException("not yet implemented")
    case Strings(strings) => throw new NotImplementedException("not yet implemented")
    case Ints(ints) => throw new NotImplementedException("not yet implemented")
    case Products(vars) => throw new NotImplementedException("not yet implemented")
    case Time() => throw new NotImplementedException("not yet implemented")

    case Rename(v, t) => throw new NotImplementedException("not yet implemented")

    case Fact(f) => throw new NotImplementedException("not yet implemented")
    case Act(a) => throw new NotImplementedException("not yet implemented")
    case Duty(d) => throw new NotImplementedException("not yet implemented")
    case Event(e) => throw new NotImplementedException("not yet implemented")

    case VarRestriction() => throw new NotImplementedException("not yet implemented")
    case FunctionRestriction() => throw new NotImplementedException("not yet implemented")

    case Dv(vars, t) => throw new NotImplementedException("not yet implemented")
    case HoldsWhen(t) => throw new NotImplementedException("not yet implemented")

    case TypeSpec(kind, domain, domain_constraint, restriction, derivation, closed, conditions)
    => throw new NotImplementedException("not yet implemented")
    case DutySpec(enforcingActs, terminatingActs, creatingActs, violatedWhen)
    => throw new NotImplementedException("not yet implemented")
    case FactSpec(invariant, actor) => throw new NotImplementedException("not yet implemented")
    case ActSpec(effects, syncs, physical) => throw new NotImplementedException("not yet implemented")
    case EventSpec(event_effects, event_syncs) => throw new NotImplementedException("not yet implemented")
    case Spec(decls, aliases) => throw new NotImplementedException("not yet implemented")

    case CAll(vars, t) => throw new NotImplementedException("not yet implemented")
    case TAll(vars, t) => throw new NotImplementedException("not yet implemented")
    case OAll(vars, t) => throw new NotImplementedException("not yet implemented")

    case Sync(vars, t) => throw new NotImplementedException("not yet implemented")


    case Trans(vars, ty) => throw new NotImplementedException("not yet implemented")
    case Query(t) => throw new NotImplementedException("not yet implemented")

    case Trigger() => throw new NotImplementedException("not yet implemented")
    case AddEvent() => throw new NotImplementedException("not yet implemented")
    case RemEvent() => throw new NotImplementedException("not yet implemented")
    case ObfEvent() => throw new NotImplementedException("not yet implemented")

    case PDo(t) => throw new NotImplementedException("not yet implemented")
    case PTrigger(vars, t) => throw new NotImplementedException("not yet implemented")
    case Create(vars, t) => throw new NotImplementedException("not yet implemented")
    case Terminate(vars, t) => throw new NotImplementedException("not yet implemented")
    case Obfuscate(vars, t) => throw new NotImplementedException("not yet implemented")
    case PQuery(t) => throw new NotImplementedException("not yet implemented")
    case PInstQuery(vars, t) => throw new NotImplementedException("not yet implemented")
    case PDeclBlock(decls) => throw new NotImplementedException("not yet implemented")
    case PSkip() => throw new NotImplementedException("not yet implemented")
    //case Seq(p1, p2) => throw new NotImplementedException("not yet implemented")

    case TypeDecl(d, ts) => throw new NotImplementedException("not yet implemented")
    case TypeExt(d, mc) => throw new NotImplementedException("not yet implemented")
    case PlaceholderDecl(d1, d2) => throw new NotImplementedException("not yet implemented")

    case ConditionedByCl(ts) => throw new NotImplementedException("not yet implemented")
    case DerivationCl(ts) => throw new NotImplementedException("not yet implemented")
    case PostCondCl(ts) => throw new NotImplementedException("not yet implemented")
    case SyncCl(ts) => throw new NotImplementedException("not yet implemented")
    case ViolationCl(ts) => throw new NotImplementedException("not yet implemented")
    case EnforcingActsCl(ts) => throw new NotImplementedException("not yet implemented")
    case TerminatedByCl(ts) => throw new NotImplementedException("not yet implemented")
    case CreatedByCl(ts) => throw new NotImplementedException("not yet implemented")
    case Not(t) => interp(t, st) match {
      case (ResBool(false), a1, st1) => (ResBool(true), a1, st1)
      case (ResBool(true), a1, st1) => (ResBool(false), a1, st1)
    }
    case Present(t) => throw new NotImplementedException("not yet implemented")
    case Violated(t) => throw new NotImplementedException("not yet implemented")
    case Enabled(t) => throw new NotImplementedException("not yet implemented")
    case BoolLit(b) => (ResBool(b), a, st)
    case StringLit(s) => (ResString(s), a, st)
    case IntLit(n) => (ResNum(n), a, st)
    case Project(t, v) => throw new NotImplementedException("not yet implemented")
    case And(t1, t2) => {
      val (ResBool(left), _, st1) = interp(t1, st)
      val (ResBool(right), _, st2) = interp(t2, st1)
      (ResBool(left && right), a, st2)
    }
    case Or(t1, t2) => {
      val (ResBool(left), _, st1) = interp(t1, st)
      val (ResBool(right), _, st2) = interp(t2, st1)
      (ResBool(left || right), a, st2)
  }
    case Leq(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResBool(left <= right), a, st2)
    }
    case Geq(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResBool(left >= right), a, st2)
    }
    case G(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResBool(left > right), a, st2)
    }
    case L(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResBool(left < right), a, st2)
    }
    case Eq(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResBool(left == right), a, st2)
    }
    case Neq(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResBool(left != right), a, st2)
    }
    case When(t1, t2) => interp(t1, st) match {
      case (ResBool(true), _, st1) => interp(t2, st1)
      case (ResBool(false), a1, st1) => (ResBool(false), a1, st1)
      case _ => throw new InterpException("when term 1 does not evaluate to bool")
    }
    case Sub(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResNum(left - right), a, st2)
    }
    case Add(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResNum(left + right), a, st2)
    }
    case Mult(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResNum(left * right), a, st2)
    }
    case Mod(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResNum(left % right), a, st2)
    }
    case Div(t1, t2) => {
      val (ResNum(left), _, st1) = interp(t1, st)
      val (ResNum(right), _, st2) = interp(t2, st1)
      (ResNum(left / right), a, st2)
    }
    case Exists(vars, t) => throw new NotImplementedException("not yet implemented")
    case Forall(vars, t) => throw new NotImplementedException("not yet implemented")
    case Count(vars, t) => throw new NotImplementedException("not yet implemented")
    case Sum(vars, t) => throw new NotImplementedException("not yet implemented")
    case Max(vars, t) => throw new NotImplementedException("not yet implemented")
    case Min(vars, t) => throw new NotImplementedException("not yet implemented")

    case _ => throw new InterpException("cannot interpret " + a)
  }

  def interp(a: AST): (Value, AST, Store) = interp(a, Nil)
}

object Explain {
  /*
    Enter phrase (for now, only consider queries concerning whether duties exist). If the duty exists in the knowledge
    base, search in the trace at what step it was created and by what act and incorporate that in the end result. Using
    the spec, list conditions (search specifically for the holds when keyword in the spec). Search for this condition
    in the knowledge base, then determine again in the trace where it is attributed a value. Use these pieces for now
    to give an explanation.

    is the knowledge from the eflint interpreter currently enough?

    example result:
    #7 > ?Duty-to-pay(Alice, VD, Sweater, 30)
    query successful:
      Act order-product creates Duty duty-to-send when in-stock is True and when time-in-minutes < 30.
      At #6 order-product(Alice, VD, Sweater)
      At #5 +in-stock()
      At #4 +time-in-minutes(0)
  */
  def explain(p: Phrase, s: List[Spec], a: AST): List[Any] = p match {
    case PDo(t) => throw new NotImplementedException("not yet implemented")
    case PTrigger(vars, t) => throw new NotImplementedException("not yet implemented")
    case Create(vars, t) => throw new NotImplementedException("not yet implemented")
    case Terminate(vars, t) => throw new NotImplementedException("not yet implemented")
    case Obfuscate(vars, t) => throw new NotImplementedException("not yet implemented")
    case PQuery(t) => throw new NotImplementedException("not yet implemented")
    case PInstQuery(vars, t) => throw new NotImplementedException("not yet implemented")
    case PDeclBlock(decls) => throw new NotImplementedException("not yet implemented")
    case PSkip() => throw new NotImplementedException("not yet implemented")
    //case Seq(p1, p2) => throw new NotImplementedException("not yet implemented")

    case _ => throw new ExplainException("cannot explain" + p)
  }

  // def prettify(res: List[Any]): String = res match {
  //   case _ => throw new NotImplementedException("TODO")
  // }
}