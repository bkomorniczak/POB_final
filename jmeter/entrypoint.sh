#!/bin/bash
echo "Starting JMeter test..."

# Uruchamiamy test JMeter w trybie non-GUI
jmeter -n -t /jmeter/testPlan.jmx -l /jmeter/results/results.jtl -e -o /jmeter/results/html-report

echo "JMeter test finished."
echo "Results saved in /jmeter/results/html-report"
