const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const fs = require('fs');

const clientPath = path.resolve(__dirname, 'src/main/front');

function filesToEntry(filePath = clientPath, prefix = '') {
    let entry = {};

    const files = fs.readdirSync(filePath);
    files.forEach(fileName => {
        const fullPath = filePath + '/' + fileName;
        const stat = fs.lstatSync(fullPath);
        if (stat.isFile()) {
            const split = fileName.split('.');
            if (split[split.length - 1] !== 'js') return;
            const name = split.slice(0, -1).join('.');
            const entryName = prefix === '' ? name : prefix + '_' + name;
            entry[entryName] = fullPath;
        } else {
            const recursivePath = filePath + '/' + fileName;
            const recursivePrefix = prefix === '' ? fileName : prefix + '_' + fileName;
            const recursiveEntry = filesToEntry(recursivePath, recursivePrefix);
            entry = {...entry, ...recursiveEntry};
        }
    });

    return entry;
}

module.exports = (env) => {
    const outputPath = path.resolve(__dirname, (env === 'production') ? 'src/main/resources/static' : 'out');

    const entry = filesToEntry();

    return {
        mode: !env ? 'development' : env,
        entry: entry,
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