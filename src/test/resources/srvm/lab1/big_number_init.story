Meta:

@author librt

Narrative:

As a librt
I want to perform a BigNumber basic actions
So that I can achieve further actions

Scenario: blank BigNumber initialization
Given a big number with size <size> and value 0
Then big number size should be <size>
And big number should be 0
Examples:
|size|
|1|
|2|
|10|
|64|
Scenario: blank BigNumber initialization with negative size
Given wrong big number with size -1 and value 0
Then big number should not be initialized
Scenario: blank BigNumber initialization without size
Given wrong big number with size 0 and value 0
Then big number should not be initialized
Scenario: blank BigNumber initialization with default size
Given a big number with value 0
When nothing is happened
Then big number size should be default
And big number should be 0
Scenario: blank BigNumber initialization from array
Given a big number from array <array>
Then big number size should be <size>
And big number should be 0
Examples:
|array|size|
|0|1|
|0,0,0|3|
Scenario: blank BigNumber initialization from string
Given a big number from string <string>
Then big number should be 0
And big number size should be <size>
Examples:
|string|size|
|0|1|
|0000000000000000|1|
|00000000000000000|2|
|0000000000000000000000000000000000000000000000000000000000000000|4|
|00000000000000000000000000000000000000000000000000000000000000000|5|
Scenario: wrong BigNumber from string
Given wrong big number from string <string>
Then big number should not be initialized
Examples:
|string|
|-|
|-1|
|-0|
|#$|
|0!%|
|(@0|
|000$000|