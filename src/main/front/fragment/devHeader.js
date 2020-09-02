window.addEventListener('load', () => {
    setIndicator();
    setDropdown();
});

function setDropdown() {
    const nicknameButton = document.getElementById('user-button');
    // 로그인 안되어있음
    if (nicknameButton == null) return;

    const dropdownMenu = document.getElementsByClassName('header-login')[0];
    nicknameButton.onclick = () => {
        dropdownMenu.style.visibility = 'visible';
    };

    document.addEventListener('click', (e) => {
        const clickOutside = !(e.path.includes(dropdownMenu) || e.path.includes(nicknameButton));
        if (clickOutside) dropdownMenu.style.visibility = 'hidden';
    });
}

function setIndicator() {
    // TODO parse url and assign selected menu index
    const selectedIndex = -1;

    const menuItems
        = /** @type Array<HTMLElement> */ Array.from(document.getElementsByClassName('header-target'));
    const indicator = document.getElementById('header-indicator');
    indicator.style.visibility = 'visible';

    const selectedMenuItem = menuItems[selectedIndex];
    matchIndicator(indicator, selectedMenuItem);

    menuItems.forEach(menuItem => {
        menuItem.addEventListener('mouseenter', () => matchIndicator(indicator, menuItem));
    });

    const menuArea = document.getElementById('header-navigator');
    menuArea.addEventListener('mouseleave', () => {
        const original = menuItems[selectedIndex];
        matchIndicator(indicator, original);
    });
}

/**
 * Matches indicator position and size to the target element
 * @param indicator {!HTMLElement}
 * @param targetElement {!HTMLElement}
 */
function matchIndicator(indicator, targetElement) {
    if (targetElement == null) {
        // 사라질 때
        indicator.style.top = TOP_HIDDEN;
        indicator.offsetHeight;
    } else {
        const anchor = targetElement.getElementsByTagName('a')[0];
        const width = anchor.offsetWidth;
        const left = anchor.getBoundingClientRect().left;
        indicator.style.width = `${width}px`;
        indicator.style.left = `${left}px`;

        if (indicator.style.top === TOP_HIDDEN) {
            // 나올 때
            indicator.classList.remove('header-indicator-smooth');
            indicator.offsetHeight;
            indicator.classList.add('header-indicator-smooth');
            indicator.style.top = TOP_VISIBLE;
        }
    }
}

const TOP_HIDDEN = '20px';
const TOP_VISIBLE = '45px';