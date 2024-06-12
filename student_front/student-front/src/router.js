import { createRouter,createMemoryHistory } from "vue-router";
import Home from "./components/Home.vue";
import Gradeinfor from "./components/Gradeinfor.vue";
import Baseinfor from "./components/Baseinfor.vue";
import ErrorMessage from "./components/ErrorMessage.vue";


const router = createRouter({
    history:createMemoryHistory(),
    routes:[
        {
        path:"/",
        component:Home
        },
        {
        path:"/",
        component:Gradeinfor
        },
        {
        path:"/",
        component:Baseinfor
        },
        {
            path:"/:pathMatch(.*)*",
            component:ErrorMessage
        }
        
    ]
});

export default router;