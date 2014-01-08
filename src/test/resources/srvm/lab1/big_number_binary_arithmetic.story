Meta:

@author librt

Narrative:

As a librt
I want to perform a BigNumber binary arithmetic
So that I can achieve further actions

Scenario: scenario description
Given big numbers <a> and <b>
Then a and b is <c>
And a or b is <d>
And a xor b is <e>
And swap works
Examples:
|a|b|c|d|e|
|0|0|0|0|0|
|1|0|0|1|1|
|0|1|0|1|1|
|1|1|1|1|0|
|0|2|0|2|2|
|1|2|0|3|3|
|10000000000000000|0|0|10000000000000000|10000000000000000|
|10000000000000000|10000000000000000|10000000000000000|10000000000000000|0|
|30000000000000000|10000000000000000|10000000000000000|30000000000000000|20000000000000000|