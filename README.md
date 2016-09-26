# metty
Simple server.
To run just type: 
```sh
$ bash httpd -c <NCPU> -r <ROOT_DIR>
```
Also can by typed -p <PORT> to specify port (Default port is 80).

Default root can be specified from this github:
```sh
$ bash httpd -c 4 -r "DOCUMENT_ROOT"
```
If everything OK, you will see message:
```sh
Server started at port: 80
CPU count is: 4
ROOTDIR is: DOCUMENT_ROOT
```
