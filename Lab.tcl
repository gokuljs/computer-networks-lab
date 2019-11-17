set ns [new Simulator]
set tf [open Lab.tr w]
$ns trace-all $tf
set nf [open Lab.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
$n0 label "Source1"
set n1 [$ns node]
$n1 label "Source2"
set n2 [$ns node]
$n2 label "Source3"
set n3 [$ns node]
$n3 label "Destination"


$ns duplex-link $n0 $n2 10Mb 300ms DropTail
$ns duplex-link $n1 $n2 10Mb 300ms DropTail
$ns duplex-link $n2 $n3 1Mb 300ms DropTail

$ns set queue-limit $n0 $n1 10
$ns set queue-limit $n1 $n2 10
$ns set queue-limit $n2 $n3 5


set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1

set null [new Agent/Null]
$ns attach-agent $n3 $null

set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0

set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1

$ns connect $udp0 $null
$ns connect $udp1 $null 

$cbr1 set packetSize_ 500mb 

$cbr1 set interval_ 0.0005


proc finish { } {

global ns tf nf 
$ns flush-trace
exec nam Lab.nam &
close $tf
close $nf
exit 0
}

$ns at 0.1 "$cbr0 start"
$ns at 0.5 "$cbr1 start"
$ns at 5.0 "finish"
$ns run
