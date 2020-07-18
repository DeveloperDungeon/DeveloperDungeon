const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = (env) => {
    const clientPath = path.resolve(__dirname, 'src/main/front');
    const outputPath = path.resolve(__dirname, (env === 'production') ? 'src/main/resources/static' : 'out');

    return {
        mode: !env ? 'development' : env,
        entry: {
            index: clientPath + '/index.js',
            login: clientPath + '/login.js',
            signup: clientPath + '/signup.js',

            quest_write: clientPath + '/quest/write.js',

            fragment_devHeader: clientPath + '/fragment/devHeader.js'
        },
        output: {
            path: outputPath,
            filename: '[name].js'
        },
        devServer: {
            contentBase: outputPath,
            publicPath: '/',
            host: '0.0.0.0',
            port: 8081,
            proxy: {
                '**': 'http://localhost:8080'
            },
            inline: true,
            hot: false
        },
        module: {
            rules: [{
                test: /\.js$/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }]
            }, {
                test: /\.(css)$/,
                use: [{
                    loader: MiniCssExtractPlugin.loader
                }, {
                    loader: 'css-loader'
                }]
            }, {
                test: /\.(jpe?g|png|gif)$/i,
                use: [{
                    loader: 'url-loader',
                    options: {
                        limit: 1024 * 10 // 10kb
                    }
                }]
            }, {
                test: /\.(svg)$/i,
                use: [{
                    loader: 'file-loader',
                    options: {
                        name: '[name].[ext]',
                        outputPath: 'images/'
                    }
                }]
            }]
        },
        plugins: [
            new MiniCssExtractPlugin({
                path: outputPath,
                filename: '[name].css'
            })
        ]
    };
};