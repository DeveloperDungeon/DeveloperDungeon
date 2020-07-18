import {redirect} from "../common/utils";

window.addEventListener('load', () => {
    document.querySelector('#main').onclick = () => {
        redirect('/');
    };

    const menuItems = Array.from(document.getElementsByClassName('header-menu'));
    menuItems.forEach(i => {
        i.onclick = () => redirect('/hyuni');
    });

    const indicator = document.getElementById('header-indicator');
    menuItems.map(i => i.getElementsByTagName('a')[0]).forEach(a => {
        a.addEventListener('mouseenter', () => {
            const width = a.offsetWidth;
            const left = a.getBoundingClientRect().left;
            indicator.style.width = `${width}px`;
            indicator.style.left = `${left}px`;
        });
    });
});