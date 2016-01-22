"use strict";

(function(){
	var rand = Math.random().toString().replace(/0.[0]*/g, '').substring(0, 8);
	return rand;
})();