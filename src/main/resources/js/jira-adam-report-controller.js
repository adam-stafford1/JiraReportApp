
console.log("IN HERE 1");
AJS.toInit(function(){
    require(['aui/flag'], function(flag) {
        var myFlag = flag({
            type: 'success',
            title: 'Reports Page',
            body: 'JS Controller is working'
        });
    });
 });