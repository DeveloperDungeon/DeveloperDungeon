window.addEventListener('load', () => {
    // TODO parse url and assign selected menu index
    const selectedIndex = -1;

    const menuItems = Array.from(document.getElementsByClassName('header-menu'));
    const indicator = document.getElementById('header-indicator');
    indicator.style.visibility = 'hidden';

    const selectedMenuItem = menuItems[selectedIndex];
    if (selectedMenuItem != null) {
        matchIndicator(indicator, selectedMenuItem.getElementsByTagName('a')[0]);
    }

    menuItems.map(i => i.getElementsByTagName('a')[0]).forEach(a => {
        a.addEventListener('mouseenter', () => matchIndicator(indicator, a));
    });
});

/**
 * Matches indicator position and size to the target element
 * @param indicator {!HTMLElement}
 * @param targetElement {!HTMLElement}
 */
function matchIndicator(indicator, targetElement) {
    const width = targetElement.offsetWidth;
    const left = targetElement.getBoundingClientRect().left;
    indicator.style.width = `${width}px`;
    indicator.style.left = `${left}px`;

    if (indicator.style.visibility === 'hidden') {
        const originalTop = indicator.style.top;
        indicator.style.top = '20px';
        indicator.offsetHeight;
        indicator.classList.toggle('header-indicator-smooth');
        indicator.style.top = originalTop;
        indicator.style.visibility = 'visible';
    }
}