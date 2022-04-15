/* Minimal top level for the Chisel Hello World.
  Wire reset to 0. */

module top(input [1:0] sw, output led);
  wire res;

  assign res = 1'h0;

  Hello h(
      .reset( res ),
      .io_sw( sw ),
      .io_led( led )
  );
endmodule
