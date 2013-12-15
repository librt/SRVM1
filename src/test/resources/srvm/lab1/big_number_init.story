Meta:

@author librt

Narrative:

As a librt
I want to perform a BigNumber basic actions
So that I can achieve further actions

Scenario: blank BigNumber initialization
Given a big number with value 0 and size 10
When I do nothing
Then big number size should be 10
Then big number should be 0

Given a big number with value 0 and size 1
When I do nothing
Then big number size should be 1
Then big number should be 0

Given a big number with value 0 and size 37
When nothing is happened
Then big number size should be 37
Then big number should be 0