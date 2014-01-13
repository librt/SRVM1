Meta:

@author librt

Narrative:

As a librt
I want to perform a BigNumber modular arithmetic
So that I can achieve end of work

Scenario: power by mod
Given big numbers <x>, <p> and <n>
Then x pow p mod n is <result>
Examples:
|x|p|n|result|
|1|1|2|1|
|2|5|2|0|
|13|10|11|1|
|FFFF|FF|FFFFFF|827508|