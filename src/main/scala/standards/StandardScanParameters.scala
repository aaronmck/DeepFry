package standards

import scala.util.matching.Regex

case object Cas9ParameterPack extends ParameterPack {
  def name = CAS9
  def pam: String = "GG"

  def totalScanLength: Int = 23
  def comparisonBitEncoding: Long = 0x3FFFFFFFFFC0l // be super careful with this value!! the cas9 mask only considers the lower 46 bites (23 of 24 bases are used)
  // and doesn't care about the NGG of NGG (3 prime) -- it's assumed all OT have the NGG
  def fivePrimePam: Boolean = false

  override def fwdRegex: Regex = """(\w[21]GG)""".r

  override def revRegex: Regex = """(CC\w[21])""".r
}
case object Cpf1ParameterPack extends ParameterPack {
  def name = CPF1
  def pam: String = "TTT"
  def totalScanLength: Int = 24
  def comparisonBitEncoding: Long = 0x00FFFFFFFFFFl // be super careful with this value!! the cas9 mask only considers the lower 46 bites (23 of 24 bases are used)
  // and doesn't care about the NGG of NGG (3 prime) -- it's assumed all OT have the NGG
  def fivePrimePam: Boolean = true

  override def fwdRegex: Regex = """(TTT\w[21])""".r

  override def revRegex: Regex = """(\w[21]AAA)""".r
}


sealed trait ParameterPack {
  def name: Enzyme

  def pam: String

  def fwdRegex: Regex

  def revRegex: Regex

  def totalScanLength: Int

  def comparisonBitEncoding: Long

  def fivePrimePam: Boolean
}

object ParameterPack {
  def nameToParameterPack(name: String): ParameterPack = name.toUpperCase match {
    case "CPF1" => Cpf1ParameterPack
    case "CAS9" => Cas9ParameterPack
    case _ => throw new IllegalStateException("Unable to find the correct parameter pack for enzyme: " + name)
  }
}

sealed trait Enzyme{def stringEncoding: String}
case object CAS9 extends Enzyme{
  override def stringEncoding: String = "Cas9"
}
case object CPF1 extends Enzyme{
  override def stringEncoding: String = "Cpf1"
}