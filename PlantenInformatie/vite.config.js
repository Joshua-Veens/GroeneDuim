import { resolve } from 'path'
import { defineConfig } from 'vite'

export default defineConfig({
    build:{
        sourcemap: true,
        rollupOptions:{
            input: {
                main: resolve(__dirname, 'index.html'),
                login: resolve(__dirname, 'login.html'),
                addplant: resolve(__dirname, 'addPlant.html'),
                editplant: resolve(__dirname, 'editplant.html'),
                removeplant: resolve(__dirname, 'removeplant.html')
            },
        },
        outDir: "../src/main/webapp/"
    },
})