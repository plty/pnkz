package hello

import chisel3._

class Mux2 extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(1.W))
    val in = Input(UInt(2.W))
    val out = Output(UInt(1.W))
  })

  io.out := (io.sel & io.in(1)) | (~io.sel & io.in(0))
}

class Mux4 extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(2.W))
    val in = Input(UInt(4.W))
    val out = Output(UInt(1.W))
  })

  val mLow0 = Module(new Mux2())
  mLow0.io.sel := io.sel(0)
  mLow0.io.in := io.in(1, 0)

  val mLow1 = Module(new Mux2())
  mLow1.io.sel := io.sel(0)
  mLow1.io.in := io.in(3, 2)

  val mHigh = Module(new Mux2())
  mHigh.io.sel := io.sel(1)
  mHigh.io.in := mLow1.io.out ## mLow0.io.out

  io.out := mHigh.io.out
}

class Hello extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(2.W))
    val btn = Input(UInt(4.W))
    val inLed = Output(UInt(4.W))
    val outLed = Output(UInt(1.W))
  })

  val in = RegInit(0.U(4.W))
  in := in ^ (io.btn & ~RegNext(io.btn))
  io.inLed := in

  val mux = Module(new Mux4())
  mux.io.sel := io.sel
  mux.io.in := in
  io.outLed := mux.io.out
}

object Hello extends App {
  (new chisel3.stage.ChiselStage).emitVerilog(new Hello())
}
