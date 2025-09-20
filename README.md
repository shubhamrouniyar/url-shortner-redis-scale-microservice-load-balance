Used Jmeter to stress the load and generate 1 Million concurrent requests.
jmeter -n -t LoadTestingUrlShortner.jmx -l results.jtl -Jjmeter.save.saveservice.output_format=xml -Jjmeter.save.saveservice.response_data=true
