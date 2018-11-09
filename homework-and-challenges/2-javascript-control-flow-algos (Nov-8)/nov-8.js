/* Homework - Control Flow Algorithms with JavaScript
 * 1. Return the nth Fibonacci number
 * 2. Sort an array of integers
 * 3. Return the factorial of an integer n
 * 4. Given an array, rotate elements to the left n times and return the array
 */

// Homework Object that Organizes all Functions
var homework = {};

// Algorithm Implementations
// Fibonacci Solution One (for loop)
homework.fibonacciOne = function(num) {
	// Input Sanitization
	if (isNaN(Number(num)) || !Number.isInteger(num) || num < 0) {
		return "Invalid Input(s)";
	}

	// For Loop Algorithm
	let current = 0;	// 0th Fibonacci number (starting point)
	let next = 1;		// 1st Fibonacci number
	let temp;

	for (let i = 0; i < num; ++i) {
		temp = next;
		next += current;	// calculate the next Fibonacci number
		current = temp;		// set the current Fibonacci number
	}

	return current;
}

// Fibonacci Solution Two (recursion with memo for performance)
homework.fibonacciTwo = function(num) {
	// Input Sanitization
	if (isNaN(Number(num)) || !Number.isInteger(num) || num < 0) {
		return "Invalid Input(s)";
	}

	// Recursive Callbacks
	let memo = [0, 1];		// memo is an array that stores previously found Fibonacci numbers starting with 0 and 1
	return homework.fibonacciMemo(num, memo);
}

// Recursive Fibonacci Function with an Array Memo
homework.fibonacciMemo = function(num, memoArr) {
	// check the memo to prevent unnecessary calls
	if (memoArr[num] !== undefined) {
		return memoArr[num];
	}
	else {
		// place the newly found Fibonacci number into the memo
		memoArr[num] = homework.fibonacciMemo(num - 1, memoArr) + homework.fibonacciMemo(num - 2, memoArr);
		return memoArr[num];
	}
}

// Sorting Solution One (bubble sort, slow)
homework.sortOne = function(inputArr) {
	// Input Sanitization
	for (let i in inputArr) {
		if (isNaN(Number(inputArr[i]))) {
			return "Invalid Input(s)";
		}
	}

	// iterate through the array
	for (let i in inputArr) {
		// for each index position, iterate through the rest of the array to search for a smaller element
		// when found, swap the smaller element with the element in the current index
		for (let j = i; j < inputArr.length; ++j) {
			if (inputArr[j] < inputArr[i]) {
				let temp = inputArr[i];
				inputArr[i] = inputArr[j];
				inputArr[j] = temp;
			}
		}
	}

	return inputArr;
}

// Sorting Solution Two (merge sort, faster)
homework.sortTwo = function(inputArr) {
	// Input Sanitization
	for (let i in inputArr) {
		if (isNaN(Number(inputArr[i]))) {
			return "Invalid Input(s)";
		}
	}

	// Initiate Merge Sort
	homework.mergeSort(inputArr);
	return inputArr;
}

homework.mergeSort = function(arr) {
	if (arr.length > 1) {
		let mid = Math.floor(arr.length / 2);		// middle point of the array

		// Divide and Conquer
		let leftArr = arr.slice(0, mid);			// first half
		let rightArr = arr.slice(mid, arr.length);	// seconf half
		// recursively call mergeSort until each partition has one element
		homework.mergeSort(leftArr);
		homework.mergeSort(rightArr);

		// Merge Partitions
		// set up three variables for tracking indices
		let l = 0, r = 0, k = 0;
		// when both partitions have unsorted elements
		while (l < leftArr.length && r < rightArr.length) {
			if (leftArr[l] < rightArr[r]) {
				arr[k] = leftArr[l];
				++l;
			}
			else {
				arr[k] = rightArr[r];
				++r;
			}
			++k;
		}

		// merge the elements that are left over
		while (l < leftArr.length) {
			arr[k] = leftArr[l];
			++l;
			++k;
		}
		while (r < rightArr.length) {
			arr[k] = rightArr[r];
			++r;
			++k;
		}
	}
}

// Factorial Solution One (for loop)
homework.factorialOne = function(num) {
	// Input Sanitization
	if (isNaN(Number(num)) || !Number.isInteger(num) || num < 0) {
		return "Invalid Input(s)";
	}
	else if (num === 0 || num === 1) {
		return 1;	// 0! = 1
	}
	
	// For Loop Algorithm
	let result = 1;
	for (let i = 1; i <= num; ++i) {
		result *= i;
	}

	return result;
}

// Factorial Solution Two (recursion)
homework.factorialTwo = function(num) {
	// Input Sanitization
	if (isNaN(Number(num)) || !Number.isInteger(num) || num < 0) {
		return "Invalid Input(s)";
	}
	else if (num === 0 || num === 1) {
		return 1;	// 0! = 1
	}

	// Recursive Callbacks
	return homework.factorialCall(num);
}

// Recursive Factorial Callback Function
homework.factorialCall = function(num) {
	if (num === 1) {
		return 1;
	}
	else {
		return num * homework.factorialCall(num - 1);
	}
}

// Array Rotation Solution One (for loop)
homework.leftRotateOne = function(inputArr, num) {
	// Input Sanitization
	if (isNaN(Number(num)) || !Number.isInteger(num)) {
		return "Invalid Input(s)";
	}
	else if (num === 0 || inputArr.length === 0 || inputArr.length === 1) {
		return inputArr;
	}
	else {
		// Enable RIght Rotation
		if (num < 0) {
			var dir = -1;
			var rot = num * -1;
			rot = rot % inputArr.length;	// eliminate redundant rotations
		}
		else {
			var dir = 1;
			var rot = num % inputArr.length;
		}
	}

	// For Loop Algorithm
	for (let i = 0; i < rot; ++i) {
		homework.rotateOnce(inputArr, dir);
	}

	return inputArr;
}

// One Time Rotation Function
homework.rotateOnce = function(inputArr, dir) {
	// Right Rotation
	if (dir === -1) {
		let temp1 = inputArr[0];
		let temp2;
		let i = 0;
		while (i < inputArr.length - 1) {
			temp2 = inputArr[i + 1];
			inputArr[i + 1] = temp1;
			temp1 = temp2;
			++i;
		}
		inputArr[0] = temp1;
	}
	// Left Rotation
	else {
		let temp1 = inputArr[inputArr.length - 1];
		let temp2;
		let i = inputArr.length - 1;
		while (i > 0) {
			temp2 = inputArr[i - 1];
			inputArr[i - 1] = temp1;
			temp1 = temp2;
			--i;
		}
		inputArr[inputArr.length - 1] = temp1;
	}
}

// Array Rotation Solution Two (index insertion)
homework.leftRotateTwo = function(inputArr, num) {
	// Input Sanitization
	if (isNaN(Number(num)) || !Number.isInteger(num)) {
		return "Invalid Input(s)";
	}
	else if (num === 0 || inputArr.length === 0 || inputArr.length === 1) {
		return inputArr;
	}
	else {
		if (num < 0) {
			var dir = -1;
			var rot = num * -1;
			rot = rot % inputArr.length;
		}
		else {
			var dir = 1;
			var rot = num % inputArr.length;
		}
	}

	let outputArr = [];
	// use a parallel array to keep track of indices
	let indArr = [];
	for (let i in inputArr) {
		indArr.push(i);
	}

	// Right Rotation
	if (dir === -1) {
		for (let i in indArr) {
			indArr[i] = parseInt(indArr[i]) + parseInt(rot);	// reason to hate javaScript's weird type interpretation
			if (indArr[i] > inputArr.length - 1) {
				indArr[i] -= inputArr.length;
			}
		}
	}
	else {
		for (let i in indArr) {
			indArr[i] -= rot;
			if (indArr[i] < 0) {
				indArr[i] += inputArr.length;
			}
		}
	}

	// insert elements into the new array with updated indices
	for (let i in indArr) {
		outputArr[indArr[i]] = inputArr[i];
	}

	return outputArr;
}

// All Test Cases
// Fibonacci Test
let fibnum1 = 2;
let fibnum2 = 5;
let fibnum3 = 10;
let fibnum4 = 20;
let fibnum5 = 50;
console.log("\n==========Fibonacci Number Finder Test==========");
console.log("\nCase 1");
console.log("Fibonacci Index: " + fibnum1 + "; Solution One: " + homework.fibonacciOne(fibnum1) + "; Solution Two: " + homework.fibonacciTwo(fibnum1));
console.log("\nCase 2");
console.log("Fibonacci Index: " + fibnum2 + "; Solution One: " + homework.fibonacciOne(fibnum2) + "; Solution Two: " + homework.fibonacciTwo(fibnum2));
console.log("\nCase 3");
console.log("Fibonacci Index: " + fibnum3 + "; Solution One: " + homework.fibonacciOne(fibnum3) + "; Solution Two: " + homework.fibonacciTwo(fibnum3));
console.log("\nCase 4");
console.log("Fibonacci Index: " + fibnum4 + "; Solution One: " + homework.fibonacciOne(fibnum4) + "; Solution Two: " + homework.fibonacciTwo(fibnum4));
console.log("\nCase 5");
console.log("Fibonacci Index: " + fibnum5 + "; Solution One: " + homework.fibonacciOne(fibnum5) + "; Solution Two: " + homework.fibonacciTwo(fibnum5));
console.log("\n");

// Sorting Test
let usArr1 = [2, 4, 1, 3, 5, 7, 9, 6, 8, 0];
let usArr2 = [55, 10];
let usArr3 = [5, 100, -90, 0.8, -7.9, 10, 11, 123];
let usArr4 = [-99.9, -10, 334, 5.5, 3, 33, 98, 221, -70];
let usArr5 = [3, 5, -9];
let usArr6 = [-4, 5.5];
let usArr7 = [50, 100, 3, -9, -8, -7, 32, 83, -38, 55, 147];
let usArr8 = [-1, -9, 0, -10, -22, -90, -55, -1000, -30];
let usArr9 = [78, 10, 49, -6, 13];
let usArr10 = [777, -66, 2, 5, 17, 76, -91, 29, 809];
console.log("\n==========Sorting Algorithm Test==========");
console.log("\nCase 1");
console.log("Before sotring: " + usArr1);
console.log("After sorting (One): " + homework.sortOne(usArr1));
console.log("\nCase 2");
console.log("Before sotring: " + usArr2);
console.log("After sorting (One): " + homework.sortOne(usArr2));
console.log("\nCase 3");
console.log("Before sotring: " + usArr3);
console.log("After sorting (One): " + homework.sortOne(usArr3));
console.log("\nCase 4");
console.log("Before sotring: " + usArr4);
console.log("After sorting (One): " + homework.sortOne(usArr4));
console.log("\nCase 5");
console.log("Before sotring: " + usArr5);
console.log("After sorting (One): " + homework.sortOne(usArr5));
console.log("\nCase 6");
console.log("Before sotring: " + usArr6);
console.log("After sorting (Two): " + homework.sortTwo(usArr6));
console.log("\nCase 7");
console.log("Before sotring: " + usArr7);
console.log("After sorting (Two): " + homework.sortTwo(usArr7));
console.log("\nCase 8");
console.log("Before sotring: " + usArr8);
console.log("After sorting (Two): " + homework.sortTwo(usArr8));
console.log("\nCase 9");
console.log("Before sotring: " + usArr9);
console.log("After sorting (Two): " + homework.sortTwo(usArr9));
console.log("\nCase 10");
console.log("Before sotring: " + usArr10);
console.log("After sorting (Two): " + homework.sortTwo(usArr10));
console.log("\n");

// Factorial Test
let facnum1 = 1;
let facnum2 = 3;
let facnum3 = 6;
let facnum4 = 10;
let facnum5 = 30;
let facnum6 = 4;
let facnum7 = 7;
let facnum8 = 15;
let facnum9 = 18;
let facnum10 = 27;
console.log("\n==========Factorial Test==========");
console.log("\nCase 1");
console.log(facnum1 + "! = " + homework.factorialOne(facnum1));
console.log("\nCase 2");
console.log(facnum2 + "! = " + homework.factorialOne(facnum2));
console.log("\nCase 3");
console.log(facnum3 + "! = " + homework.factorialOne(facnum3));
console.log("\nCase 4");
console.log(facnum4 + "! = " + homework.factorialOne(facnum4));
console.log("\nCase 5");
console.log(facnum5 + "! = " + homework.factorialOne(facnum5));
console.log("\nCase 6");
console.log(facnum6 + "! = " + homework.factorialTwo(facnum6));
console.log("\nCase 7");
console.log(facnum7 + "! = " + homework.factorialTwo(facnum7));
console.log("\nCase 8");
console.log(facnum8 + "! = " + homework.factorialTwo(facnum8));
console.log("\nCase 9");
console.log(facnum9 + "! = " + homework.factorialTwo(facnum9));
console.log("\nCase 10");
console.log(facnum10 + "! = " + homework.factorialTwo(facnum10));
console.log("\n");

// Array Rotation Test
let rotArr1 = [1, 4, 7, 9, 12, 6]
let rot1 = 1;
let rotArr2 = [11, 55, 27, -9, 2, 0.9]
let rot2 = 2;
let rotArr3 = ["2", "abc", "6", 76, true, "sad"];
let rot3 = -3;
let rotArr4 = [7, 9, 13, 25, 27];
let rot4 = 4;
let rotArr5 = ["xy", "foo", "123", 70];
let rot5 = 5;
let rotArr6 = [5, 9, 0, 8, 4, "3", 7, 2, 107];
let rot6 = -6;
console.log("\n==========Array Rotation Test==========");
console.log("\nCase 1");
console.log("Before rotation: " + rotArr1);
console.log("After " + rot1 + " rotations: " + homework.leftRotateOne(rotArr1, rot1));
console.log("\nCase 2");
console.log("Before rotation: " + rotArr2);
console.log("After " + rot2 + " rotations: " + homework.leftRotateOne(rotArr2, rot2));
console.log("\nCase 3");
console.log("Before rotation: " + rotArr3);
console.log("After " + rot3 + " rotations: " + homework.leftRotateOne(rotArr3, rot3));
console.log("\nCase 4");
console.log("Before rotation: " + rotArr4);
console.log("After " + rot4 + " rotations: " + homework.leftRotateTwo(rotArr4, rot4));
console.log("\nCase 5");
console.log("Before rotation: " + rotArr5);
console.log("After " + rot5 + " rotations: " + homework.leftRotateTwo(rotArr5, rot5));
console.log("\nCase 6");
console.log("Before rotation: " + rotArr6);
console.log("After " + rot6 + " rotations: " + homework.leftRotateTwo(rotArr6, rot6));
console.log("\n");