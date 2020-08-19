describe("Hello world", function() {
    it("says hello", function() {
        expect(requestNewQuest('test', 'wow')).toEqual('성공');
    });
});