# CPCDS to FHIR Converter

This is an example project for converting CPCDS CSV to FHIR ExplanationOfBenefit. It reads a CSV file and then posts  
the data into a running FHIR Server.

## Note

This is not an official reference implementations of maintained by CARIN. It was developed as a Proof of Concept 
and should not be assumed to be exhaustive. 

## Pre-requisites

### FHIR Server

This requires a FHIR Server to post the data to. A good option is Hapi FHIR Server which you 
can find [here](https://github.com/hapifhir/hapi-fhir-jpaserver-starter).
                                                                  
To run it, clone that repo and run:

```mvn jetty:run```

### Test Data 

To generate test CPCDS data you can use Synthea. It can be cloned 
from [here](https://github.com/synthetichealth/synthea).

The commands to get and build are:
```
git clone https://github.com/synthetichealth/synthea.git
cd synthea
./gradlew build check test
``` 
Once it is built use the following command to generate a small file:

```./run_synthea --exporter.cpcds.export true```

This command will generate a VERY large file:
```./run_synthea -p 5000 --exporter.cpcds.export true```

## Running 

To run this tool you need use the command:

```gradle execute --args="-i [CPCDS CSV File] -f [FHIR Server base URL]"```