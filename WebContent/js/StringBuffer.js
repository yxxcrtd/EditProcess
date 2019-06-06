function StringBuffer() {
	this._buffers = [];
	this._length = 0;
	this._splitChar = arguments.length > 0 ? arguments[arguments.length - 1] : '';
	if (arguments.length > 0) {
		for ( var i = 0, iLen = arguments.length - 1; i < iLen; i++) {
			this.append(arguments[i]);
		}
	}
}

StringBuffer.prototype.append = function() {
	var str = null;
	if(arguments.length == 1) {
		str = arguments[0];
	} else if(arguments.length == 2) {
		str = arguments[0];
		var params = arguments[1];
		for (p in params) {
			str = str.replace(eval("/\\{" + p + "\\}/g;"), params[p]);
		}
	}
	this._length += str.length;
	this._buffers[this._buffers.length] = str;
	return this;
}
StringBuffer.prototype.add = StringBuffer.prototype.append;

StringBuffer.prototype.format = function() {
	var str = this.toString();
	if(arguments.length == 1) {
		var params = arguments[0];
		for (p in params) {
			str = str.replace(eval("/\\{" + p + "\\}/g;"), params[p]);
		}
	}
	return str;
}

StringBuffer.prototype.length = function() {
	if (this._splitChar.length > 0 && (!this.IsEmpty())) {
		return this._length + (this._splitChar.length * (this._buffers.length - 1));
	} else {
		return this._length;
	}
}

StringBuffer.prototype.isEmpty = function() {
	return this._buffers.length <= 0;
}

StringBuffer.prototype.clear = function() {
	this._buffers = [];
	this._length = 0;
}

StringBuffer.prototype.toString = function() {
	if (arguments.length == 1) {
		return this._buffers.join(arguments[1]);
	} else {
		return this._buffers.join(this._splitChar);
	}
}