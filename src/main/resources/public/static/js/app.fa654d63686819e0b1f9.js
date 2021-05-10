webpackJsonp([1],{"3Py5":function(e,t){},"5N+c":function(e,t){},NHnr:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("7+uW"),s={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]};var r=a("VU/8")({name:"App"},s,!1,function(e){a("nF9L")},null,null).exports,o=a("/ocq"),i=a("mtWM"),u=a.n(i),l=a("mvHQ"),m=a.n(l),c=a("Zrlr"),d=a.n(c),p=a("wxAW"),v=a.n(p),h=a("u2KI"),f=a.n(h)()({API_USER:"http://localhost:8080/api/user/",API_CHAT:"http://localhost:8080/api/chat",API_REGISTRATION:"http://localhost:8080/api/registration",API_AUTH:"http://localhost:8080/api/auth",API_USER_ALL:"http://localhost:8080/api/user/all"}),g=new(function(){function e(){d()(this,e)}return v()(e,[{key:"login",value:function(e){return u.a.post(f.API_AUTH,{username:e.username,password:e.password}).then(function(e){return e.data.token&&localStorage.setItem("user",m()(e.data)),e.data})}},{key:"logout",value:function(){localStorage.removeItem("user")}}]),e}()),_={name:"Main",data:function(){return{clientLogged:!1,form:{username:"",password:""}}},methods:{login:function(){var e=this;g.login(this.form).then(function(t){t.token&&e.$router.push({name:"Chat"})})},redirectToRegistration:function(){}}},N={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"main"},[a("h2",[e._v("Přihlášení do Žvanila")]),e._v(" "),a("form",{on:{submit:function(t){return t.preventDefault(),e.login(t)}}},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.form.username,expression:"form.username"}],attrs:{type:"text",id:"username",placeholder:"Jak na tebe hulákaj"},domProps:{value:e.form.username},on:{input:function(t){t.target.composing||e.$set(e.form,"username",t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.form.password,expression:"form.password"}],attrs:{type:"text",id:"password",placeholder:"Tady napiš heslo"},domProps:{value:e.form.password},on:{input:function(t){t.target.composing||e.$set(e.form,"password",t.target.value)}}}),e._v(" "),a("button",[e._v("Tak hurá do kecání")])]),e._v(" "),a("router-link",{attrs:{to:{name:"Registration"}}},[e._v("Když nevíš klikni sem")])],1)},staticRenderFns:[]};var U=a("VU/8")(_,N,!1,function(e){a("p1my")},"data-v-60a7243a",null).exports,w={name:"Registration",data:function(){return{form:{username:"",password:""}}},methods:{submitRegistraion:function(){var e=this;u.a.post(f.API_REGISTRATION,this.form).then(function(t){200===t.status&&e.$router.push({name:"Main"})})}}},k={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"registration"},[a("h2",[e._v("Nějaká registrace")]),e._v(" "),a("form",{on:{submit:function(t){return t.preventDefault(),e.submitRegistraion(t)}}},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.form.username,expression:"form.username"}],attrs:{type:"text",id:"username",placeholder:"Jak na tebe hulákaj"},domProps:{value:e.form.username},on:{input:function(t){t.target.composing||e.$set(e.form,"username",t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.form.password,expression:"form.password"}],attrs:{type:"text",id:"password",placeholder:"Doplň Heslo nebo 123"},domProps:{value:e.form.password},on:{input:function(t){t.target.composing||e.$set(e.form,"password",t.target.value)}}}),e._v(" "),a("button",[e._v("Upiš se!")])]),e._v(" "),a("router-link",{attrs:{to:{name:"Main"}}},[e._v("Ale tady jsem už byl.")])],1)},staticRenderFns:[]};var A=a("VU/8")(w,k,!1,function(e){a("Sog7")},"data-v-49cf8bed",null).exports;function P(){var e=JSON.parse(localStorage.getItem("user"));return e&&e.token?{Authorization:"Bearer "+e.token}:{}}var y={name:"UserSelector",data:function(){return{toUserName:"",userSelectorText:"S kým budeme žvanit",userList:[]}},methods:{toUserNameChanged:function(e){var t=this;e&&u.a.get(f.API_USER+"/"+e.target.value,{headers:P()}).then(function(e){200===e.status&&t.$emit("changed",e.data.username)}).catch(function(e){404===e.response.status&&(t.toUserName="",t.userSelectorText="Nikdo takový neexistuje ! Zkus znovu.")})},loadUserList:function(){var e=this;u.a.get(f.API_USER_ALL,{headers:P()}).then(function(t){200===t.status&&(e.userList=t.data)})},toUserNamePicked:function(e){e&&this.$emit("changed",e)}},mounted:function(){this.loadUserList()}},L={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"userSelector"},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.toUserName,expression:"toUserName"}],attrs:{type:"text",placeholder:e.userSelectorText},domProps:{value:e.toUserName},on:{change:e.toUserNameChanged,input:function(t){t.target.composing||(e.toUserName=t.target.value)}}}),e._v(" "),a("div",{staticClass:"userList"},[a("h3",[e._v("Seznam Žvanilů")]),e._v(" "),e._l(e.userList,function(t){return a("a",{key:t.username,attrs:{href:""},on:{click:function(a){return a.preventDefault(),e.toUserNamePicked(t.username)}}},[e._v(e._s(t.username))])})],2)])},staticRenderFns:[]};var x={name:"Chat",components:{UserSelector:a("VU/8")(y,L,!1,function(e){a("3Py5")},"data-v-1fd2b2a0",null).exports},data:function(){return{toUserName:null,chatList:[],newMessage:""}},methods:{toUserNameFilled:function(e){this.toUserName=e,console.log(e),this.loadChatList(),this.setPeriodicLoad()},setPeriodicLoad:function(){var e=this;this.interval=setInterval(function(){return e.loadChatList()},1e3)},loadChatList:function(){var e=this;u.a.get(f.API_CHAT+"?withUsername="+this.toUserName,{headers:P()}).then(function(t){200===t.status&&(e.chatList=t.data)})},sendMessage:function(){var e=this;if(this.toUserName&&this.newMessage){var t={toUserName:this.toUserName,message:this.newMessage};u.a.post(f.API_CHAT,t,{headers:P()}).then(function(t){200===t.status&&(console.log("sended"),e.newMessage="",loadChatList())})}}}},C={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"chat"},[null!=e.toUserName?a("h2",[e._v("Žvanění s "+e._s(e.toUserName))]):a("h2",[e._v("Žvanění")]),e._v(" "),null==e.toUserName?a("UserSelector",{on:{changed:e.toUserNameFilled}}):e._e(),e._v(" "),e.chatList&&e.chatList.length?a("div",{staticClass:"chatlist"},e._l(e.chatList,function(t,n){return a("div",{key:"message-"+n},[t.userName==e.toUserName?a("span",{staticClass:"right"},[e._v(e._s(t.userName)+": "+e._s(t.message))]):a("span",{staticClass:"left"},[e._v(e._s(t.userName)+" : "+e._s(t.message))])])}),0):e._e(),e._v(" "),null!=e.toUserName?a("div",{on:{submit:function(t){return t.preventDefault(),e.sendMessage(t)}}},[a("form",{on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.sendMessage(t)}}},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.newMessage,expression:"newMessage"}],attrs:{type:"text",placeholder:""},domProps:{value:e.newMessage},on:{input:function(t){t.target.composing||(e.newMessage=t.target.value)}}}),e._v(" "),a("button",[e._v("Odeslat žvanění")])])]):e._e()],1)},staticRenderFns:[]};var S=a("VU/8")(x,C,!1,function(e){a("5N+c")},"data-v-22ea46d2",null).exports;n.a.use(o.a);var I=new o.a({routes:[{path:"/",name:"Main",component:U},{path:"/registration",name:"Registration",component:A},{path:"/chat",name:"Chat",component:S}]});n.a.config.productionTip=!1,new n.a({el:"#app",router:I,components:{App:r},template:"<App/>"})},Sog7:function(e,t){},nF9L:function(e,t){},p1my:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.fa654d63686819e0b1f9.js.map