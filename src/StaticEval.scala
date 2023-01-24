import Types.DomId

class StaticEvalException(s: String) extends RuntimeException(s)
class NotImplementedException(s: String) extends RuntimeException(s)

abstract class M_Stc
//case class a

object StaticEval {
  def compile_kind (d: DomId, t: TypeSpec, k: Kind): Kind = t match {

  }
}
