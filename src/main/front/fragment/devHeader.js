window.addEventListener('load', () => {
    const menuItems = Array.from(document.getElementsByClassName('header-menu'));
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