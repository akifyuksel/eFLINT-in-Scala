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
  def explain(p: Phrase, s: List[Spec], Trace: List[String], KnowledgeBase: List[Term]): List[Any] = p match {
    case _ => throw new NotImplementedException("TODO")
  }

  def prettify(res: List[Any]): String = res match {
    case _ => throw new NotImplementedException("TODO")
  }
}