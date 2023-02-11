# plusOne
from typing import List

def plusOne(digits: List[int]) -> List[int]:
    size = len(digits) - 1
    sum = 0
    multiplier = 1
    while (size >= 0):
        sum = sum + (digits[size] * multiplier)
        multiplier = multiplier * 10
        size = size - 1
    sumString = str(sum + 1)
    returnList = map(int, str(sumString))
    return returnList
