class ExplainException(s: String) extends RuntimeException(s)

object parseJSON {
  def parseJSON(in: JSON): (Phrase, List[Spec], AST) = in match {
    case _ => throw new NotImplementedException("not yet implemented")
  }
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
    case PSeq(p1, p2) => explain(p1, s, a) ::: explain(p2, s,a)

    case _ => throw new ExplainException("cannot explain" + p)
  }

  // def prettify(res: List[Any]): String = res match {
  //   case _ => throw new NotImplementedException("TODO")
  // }
}