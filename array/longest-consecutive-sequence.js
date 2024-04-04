function longestConsecutive (nums) {
    const set = new Set(nums);
    let maxLen = 0;

    for (let i = 0; i < nums.length; i++) {
        let currNum = nums[i];
        let currLen = 0;
        if (!set.has(currNum + 1)) {
            while (set.has(currNum)) {
                currLen++;
                currNum--;
            }
        }
        if (currLen > maxLen) maxLen = currLen
    }

    return maxLen;
}
