import Vue from "vue";
import VueRouter from "vue-router";
import DaYe from "@/components/DaYe";
import LianJi from "@/components/LianJi";
import RenWu from "@/components/RenWu";

Vue.use(VueRouter)
const vueRouter = new VueRouter({
    routes: [
        {path: '*', component: RenWu},
        {path: '/0', component: RenWu},
        {path: '/1', component: DaYe},
        {path: '/2', component: LianJi}
    ]
});
export default vueRouter
