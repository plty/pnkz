/* Minimal top level for the Chisel Hello World.
  Wire reset to 0. */

module top(input a, input b, output led);
  wire res;

  assign res = 1'h0;

  Hello h(
      .reset( res ),
      .io_a( a ),
      .io_b( b ),
      .io_out( led )
  );
endmodule
