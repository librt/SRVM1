Meta:

@author librt

Narrative:

As a librt
I want to perform a BigNumber basic actions
So that I can achieve further actions

Scenario: blank BigNumber initialization
Given a big number with size 10 and value 0
When I do nothing
Then big number size should be 10
Then big number should be 0
Scenario: blank BigNumber initialization with size 1
Given a big number with size 1 and value 0
When I do nothing
Then big number size should be 1
Then big number should be 0
Scenario: blank BigNumber initialization with negative size
Given wrong big number with size -1 and value 0
Then big number should not be initialized
Scenario: blank BigNumber initialization without size
Given wrong big number with size 0 and value 0
Then big number should not be initialized
Scenario: blank BigNumber initialization with default size
Given a big number with value 0
When nothing is happened
Then big number should be 0
Scenario: blank BigNumber initialization from minimal array
Given a big number from array 0
Then big number size should be 1
Then big number should be 0
Scenario: blank BigNumber initialization from array
Given a big number from array 0,0,0
Then big number size should be 3
Then big number should be 0
Scenario: blank BigNumber initialization from string
Given a big number from string 0
Then big number should be 0
Then big number size should be 1
Scenario: blank BigNumber initialization from string with 16 digits (for one Long)
Given a big number from string 0000000000000000
Then big number should be 0
Then big number size should be 1
Scenario: blank BigNumber initialization from string with 17 digits (two Long)
Given a big number from string 00000000000000000
Then big number should be 0
Then big number size should be 2
Scenario: blank BigNumber initialization from string with 64 digits (four Long)
Given a big number from string 0000000000000000000000000000000000000000000000000000000000000000
Then big number should be 0
Then big number size should be 4