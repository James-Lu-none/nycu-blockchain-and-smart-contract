// SPDX-License-Identifier: GPL-3.0
pragma solidity >= 0.8.0;

contract Message {
	string private message;
	address owner;
	constructor() {
		owner = msg.sender;
	}

	modifier ownerOnly() {
		require(msg.sender == owner);
		_;
	}
	
	function get() public view returns (string memory) {
		return message;
	}
	function set(string memory set_message) public ownerOnly {
		message = set_message;
	}
}
