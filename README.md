# 20201_PTPMPT_LTU15C_Nhom3
* Server
build image:  docker build -t server ./server
run container: docker run -it --rm --name server server
* Client (chú ý share folder result trong client)
build image:  docker build -t client ./client
run container:  docker run -it --rm --name client -v $PWD\client\result:/src/result client



  
