package com.cricketGame.cricketgame.utils;


public  class GenerateRandom {

    // Utility function to find ceiling of r in arr[l..h]
    static int findCeil(int arr[], int r, int l, int h) {
        int mid;
        while (l < h) {
            mid = l + ((h - l) >> 1); // Same as mid = (l+h)/2
            if (r > arr[mid])
                l = mid + 1;
            else
                h = mid;
        }
        return (arr[l] >= r) ? l : -1;
    }

    // The main function that returns a random number
// from arr[] according to distribution array
// defined by freq[]. n is size of arrays.
    static int myRand(int arr[], int freq[], int n) {
        // Create and fill prefix array
        int prefix[] = new int[n], i;
        prefix[0] = freq[0];
        for (i = 1; i < n; ++i)
            prefix[i] = prefix[i - 1] + freq[i];

        // prefix[n-1] is sum of all frequencies.
        // Generate a random number with
        // value from 1 to this sum
        int r = ((int) (Math.random() * (323567)) % prefix[n - 1]) + 1;

        // Find index of ceiling of r in prefix array
        int indexc = findCeil(prefix, r, 0, n - 1);
        return arr[indexc];
    }

    public static  Object randomBallGenerator(int batsmanRating, int ballerRating) {
        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7};
        int ii = batsmanRating - ballerRating;
//        int x=ii,y=10-ii;
        int[] freq;
        if (ii > 0) {
            freq = new int[]{5, 5, 5, 5, 25, 25, 25, 5};
        } else if (ii == 0) {
            freq = new int[]{12, 13, 12, 13, 12, 13, 12, 13};
        } else {
            freq = new int[]{14, 14, 14, 14, 5, 5, 5, 29};
        }
        int result = GenerateRandom.myRand(arr, freq, 8);
        if (result == 7) return 'W';
        return result;
//
    }

    public static int randomRatingGenerator() {
        return ((int) (Math.random() * 11)) % 10;
    }
}
