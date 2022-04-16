/* Minimal top level for the Chisel Hello World.
  Wire reset to 0. */

module top(input clk, input [1:0] sw, input [3:0] btn, output [3:0] inled, output outled);
  wire res;

  assign res = 1'h0;

  Hello h(
      .clock( clk ),
      .reset( res ),
      .io_sel( sw ),
      .io_btn( btn ),
      .io_inLed( inled ),
      .io_outLed( outled )
  );
endmodule
