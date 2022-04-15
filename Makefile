all: hdl bitstream

hdl:
	sbt run

bitstream:
	docker run --rm -it \
		--volume ${CURDIR}:/app plty/ffffpga:xc7z020 \
		TOP=top \
		make

flash:
	openocd -f ./pynq.cfg -c "init; pld load 0 build/pynq-z2/top.bit; exit"
