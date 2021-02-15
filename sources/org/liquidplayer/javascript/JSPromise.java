package org.liquidplayer.javascript;

public class JSPromise extends JSObject {
    private final JSObject promiseObject;

    public JSPromise(JSContext jSContext) {
        this.context = jSContext;
        JSObject object = this.context.evaluateScript("(()=>{  var po = {}; var clock = true;  var timer = setInterval(()=>{if(!clock) clearTimeout(timer);}, 100);   po.promise = new Promise((resolve,reject)=>{po.resolve=resolve;po.reject=reject});  po.promise.then(()=>{clock=false}).catch(()=>{clock=false});  return po;})();").toObject();
        this.promiseObject = object;
        this.valueRef = object.property("promise").toObject().valueRef();
        addJSExports();
        this.context.persistObject(this);
    }

    public void resolve(Object... objArr) {
        if (objArr == null) {
            objArr = new Object[0];
        }
        this.promiseObject.property("resolve").toFunction().apply((JSObject) null, objArr);
    }

    public void reject(Object... objArr) {
        if (objArr == null) {
            objArr = new Object[0];
        }
        this.promiseObject.property("reject").toFunction().apply((JSObject) null, objArr);
    }
}
