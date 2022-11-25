class ParseException(s: String) extends RuntimeException(s)
//class ReadException(s: String) extends ParseException(s)

//SExpr - Symbolic expression.
abstract class SExpr
case class SNum(n: Int) extends SExpr
case class SSym(s:String) extends SExpr
case class SList(list: List[SExpr]) extends SExpr

object parse {
  def parse(s: SExpr): AST = s match {
    case SNum(n) => IntLit(n)
    case SSym("True") => BoolLit(true)
    case SSym("False") => BoolLit(false)
    case SSym(x) if AST.keywords.contains(x) => throw new ParseException(x +  " cannot be used as an identifier")
    case SSym(x) => Var(x, "idk what to put here")
    case SList(a1 :: SSym(binop) :: a2 :: Nil) =>
      val l = parse(a1)
      val r = parse(a2)
      val test = IntLit(1)
      if (l.getClass != test.getClass) throw new ParseException("Not a term: " + a1)
      if (r.getClass != test.getClass) throw new ParseException("Not a term: " + a2)
      val lTerm = l.asInstanceOf[Term]
      val rTerm = l.asInstanceOf[Term]
      binop match {
        case "When" => When(lTerm, rTerm)
        case "||" => Or(lTerm, rTerm)
        case "&&" => And(lTerm, rTerm)
        case "==" => Eq(lTerm, rTerm)
        case "!=" => Neq(lTerm, rTerm)
        case ">=" => Geq(lTerm, rTerm)
        case "<=" => Leq(lTerm, rTerm)
        case "<" => L(lTerm, rTerm)
        case ">" => G(lTerm, rTerm)
        case "-" => Sub(lTerm, rTerm)
        case "+" => Add(lTerm, rTerm)
        case "*" => Mult(lTerm, rTerm)
        case "%" => Mod(lTerm, rTerm)
        case "/" => Div(lTerm, rTerm)
        case _ => throw new ParseException("unrecognized keyword for binary operation " + binop)
      }
    case SList(SSym(unop) :: a :: Nil) =>
      val e = parse(a)
      val test = IntLit(1)
      if (e.getClass != test.getClass) throw new ParseException("Not a Term: " + a)
      val eTerm = e.asInstanceOf[Term]
      unop match {
        case "!" => Not(eTerm)
        case "Not" => Not(eTerm)
        case "Present" => Present(eTerm)
        case "Violated" => Violated(eTerm)
        case "Enabled" => Enabled(eTerm)
      }

    case _ => throw new ParseException("cannot parse " + s)
  }
}