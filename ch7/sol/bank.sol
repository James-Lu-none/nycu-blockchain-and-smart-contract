//SPDX-License-Identifier: GPL-3.0

pragma solidity >= 0.8.0;

contract Bank {
	address bankOwner;
	uint public transactions;
	string public bankName;
	mapping(address=>uint) balances;

	constructor() {
		bankOwner = msg.sender;
	}

	modifier ownerOnly() {
		require(msg.sender == bankOwner);
		_;
	}

	function deposit() public payable {
		balances[msg.sender] += msg.value;
		transactions++;
	}

	function getBalance() public view returns (uint) {
		return balances[msg.sender];
	}

	function withdraw(uint amount) public {
		require(balances[msg.sender] >= amount);
		balances[msg.sender] -= amount;
		payable(msg.sender).transfer(amount);
		transactions++;
	}

	function setBankName(string memory newBankName) public ownerOnly {
		bankName = newBankName;
	}
	
	function getTotalBalance() public view ownerOnly returns (uint) {
		return address(this).balance;
	}
}
