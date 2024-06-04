import { resolve } from 'path'
import { defineConfig } from 'vite'

export default defineConfig({
    build:{
        sourcemap: true,
        rollupOptions:{
            input: {
                main: resolve(__dirname, 'index.html')
                // to add more html pages....see FEP1 boilerplate section build&deploy
            },
        },
        outDir: "../src/main/webapp/"
    },
})