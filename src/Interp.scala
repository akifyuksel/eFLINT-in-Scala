class InterpException(s: String) extends RuntimeException(s)
class NotImplementedException(s: String) extends RuntimeException(s)
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

    case Fact(f) => interp(f, st) match {
      case (ResString(str), a1, st1) => (ResString("FACT " + str), Seq(a, a1), st1)
    }
    case Act(a) => interp(a, st) match {
      case (ResString(str), a1, st1) => (ResString("FACT " + str), Seq(a, a1), st1)
    }
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
    case FactSpec(invariant, actor) => (ResString("factspec " + invariant + " " + actor), a, st)
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
      case (ResBool(false), a1, st1) => (ResBool(true), Seq(a, a1), st1)
      case (ResBool(true), a1, st1) => (ResBool(false), Seq(a, a1), st1)
    }
    case Present(t) => throw new NotImplementedException("not yet implemented")
    case Violated(t) => throw new NotImplementedException("not yet implemented")
    case Enabled(t) => throw new NotImplementedException("not yet implemented")
    case BoolLit(b) => (ResBool(b), a, st)
    case StringLit(s) => (ResString(s), a, st)
    case IntLit(n) => (ResNum(n), a, st)
    case Project(t, v) => throw new NotImplementedException("not yet implemented")
    case And(t1, t2) => {
      val (ResBool(left), a1, st1) = interp(t1, st)
      val (ResBool(right), a2, st2) = interp(t2, st1)
      (ResBool(left && right), Seq(a, Seq(a1, a2)), st2)
    }
    case Or(t1, t2) => {
      val (ResBool(left), a1, st1) = interp(t1, st)
      val (ResBool(right), a2, st2) = interp(t2, st1)
      (ResBool(left || right), Seq(a, Seq(a1, a2)), st2)
    }
    case Leq(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResBool(left <= right), Seq(a, Seq(a1, a2)), st2)
    }
    case Geq(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResBool(left >= right), Seq(a, Seq(a1, a2)), st2)
    }
    case G(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResBool(left > right), Seq(a, Seq(a1, a2)), st2)
    }
    case L(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResBool(left < right), Seq(a, Seq(a1, a2)), st2)
    }
    case Eq(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResBool(left == right), Seq(a, Seq(a1, a2)), st2)
    }
    case Neq(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResBool(left != right), Seq(a, Seq(a1, a2)), st2)
    }
    case When(t1, t2) => interp(t1, st) match {
      case (ResBool(true), a1, st1) => interp(t2, st1)
      case (ResBool(false), a1, st1) => (ResBool(false), Seq(a, a1), st1)
      case _ => throw new InterpException("when term 1 does not evaluate to bool")
    }
    case Sub(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResNum(left - right), Seq(a, Seq(a1, a2)), st2)
    }
    case Add(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResNum(left + right), Seq(a, Seq(a1, a2)), st2)
    }
    case Mult(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResNum(left * right), Seq(a, Seq(a1, a2)), st2)
    }
    case Mod(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResNum(left % right), Seq(a, Seq(a1, a2)), st2)
    }
    case Div(t1, t2) => {
      val (ResNum(left), a1, st1) = interp(t1, st)
      val (ResNum(right), a2, st2) = interp(t2, st1)
      (ResNum(left / right), Seq(a, Seq(a1, a2)), st2)
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
