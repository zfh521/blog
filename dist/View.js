"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var View = function () {
  _createClass(View, [{
    key: "getDefaultProps",
    value: function getDefaultProps() {
      return {
        name: "123"
      };
    }
  }]);

  function View() {
    _classCallCheck(this, View);

    this.name = "hello";
  }

  _createClass(View, [{
    key: "init",
    value: function init() {
      console.log("init function");
    }
  }]);

  return View;
}();