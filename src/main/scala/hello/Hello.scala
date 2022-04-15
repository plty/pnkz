package hello

import chisel3._

/**
  * Compute GCD using subtraction method.
  * Subtracts the smaller from the larger until register y is zero.
  * value in register x is then the GCD
  */
class Hello extends Module {
  val io = IO(new Bundle {
    val a = Input(Bool())
    val b = Input(Bool())
    val out = Output(Bool())
  })

  io.out := io.a ^ io.b;
}

/**
  * An object extending App to generHelloate the Verilog code.
  */
object Hello extends App {
  (new chisel3.stage.ChiselStage).emitVerilog(new Hello())
}
