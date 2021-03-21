# TQS_Practical 93106

Q> Which classes/methods offer less coverage? Are all possible decision branches being covered?

A> 

To generate the JaCoCo report we use the command mvn test jacoco:report

The class that offered the least coverage was the CuponEuromillions which had 40% missed instructions coverage and 0% missed branches coverage because 'format()' was not tested.

Most of the decision branches were covered but there are still some branches that haven't been, mainly because the auto-generated methods in the 'Dip' class were not tested.
