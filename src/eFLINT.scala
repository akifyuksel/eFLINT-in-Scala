class NotImplementedException(s: String) extends RuntimeException(s)

sealed abstract class Phrase
case class trigger(vars: List[String], t: Term) extends Phrase
case class create(vars: List[String], t: Term) extends Phrase
case class terminate(vars: List[String], t: Term) extends Phrase
case class query(t: Term) extends Phrase
case class instQuery(vars: List[String], t: Term) extends Phrase
case class Seq(p1: Phrase, p2: Phrase) extends Phrase

sealed abstract class Spec
case class Duty(name: String) extends Spec
case class Fact(name: String) extends Spec
case class Act(name: String) extends Spec
case class BoolLiteral(name:String, b: Boolean) extends Spec

sealed abstract class Term
case class Not(t: Term) extends Term
case class Present(t:Term) extends Term
case class Violated(t: Term) extends Term
case class Enabled(t: Term) extends Term

case class BoolLit(b: Boolean) extends Term
case class StringList(s: String) extends Term

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
  }

  def prettify(res: List[Any]): String = res match {
    case _ => throw new NotImplementedException("TODO")
  }
}