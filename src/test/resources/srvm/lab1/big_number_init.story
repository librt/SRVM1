Meta:

@author librt

Narrative:

As a librt
I want to perform a BigNumber basic actions
So that I can achieve further actions

Scenario: BigNumber initialization
Given a big number with size <size> and value <value>
Then big number size should be <size>
And big number should be <number>
Examples:
|size|value|number|
|1|1|1|
|2|1|1|
|10|1|1|
|64|1|1|
Scenario: BigNumber initialization from array
Given a big number from array <array>
Then big number size should be <size>
And big number should be <number>
Examples:
|array|size|number|
|1|1|1|
|1,0,0|3|1|
|1,0,1|3|100000000000000000000000000000001|
|9007199254740990|1|1FFFFFFFFFFFFE|
Scenario: BigNumber initialization from string
Given a big number from string <string>
Then big number should be <number>
And big number size should be <size>
Examples:
|string|size|number|
|1|1|1|
|0000000100000000|1|100000000|
|00e0000000000000f|2|E0000000000000F|
|1000000100000000000000000000000000000000000fE0000000000000000001|4|1000000100000000000000000000000000000000000FE0000000000000000001|
|f0000000000000000000000000000000000000000000000000000000000000000|5|F0000000000000000000000000000000000000000000000000000000000000000|