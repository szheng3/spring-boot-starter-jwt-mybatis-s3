#!/usr/bin/expect
set timeout -1

set password [lindex $argv 0]
spawn mvn test
expect "*BUILD SUCCESS*"
send "echo 'gg'\r"
spawn ssh -p 22 root@172.105.198.16
expect "*password*"
send "$password\r"
expect "#"
send  "cd accounting-back-end/\r"
send  "make run\r"
send  "exit\r"
interact #操作完成

