(function () {
    var scraperService = function () {
        return {
            getImages: function (content) {

                var parseImages = function (response) {
                    var tmp = document.implementation.createHTMLDocument();
                    tmp.body.innerHTML = response;

                    var images = tmp.getElementsByTagName("img");
                    var srcList = [];
                    for (var i = 0; i < images.length; i++) {
                        if((images[i].src).search("http:") !== -1 
                                || (images[i].src).search("https:") !== -1){
                            srcList.push(images[i].src);
                        }
                        else{
                            srcList.push("http:"+images[i].src);
                        }
                    }

                    return srcList;
                };
                return parseImages(content);
            },
            getText: function (content) {

                function getTextFromNode(node, addSpaces) {
                    var i, result, text, child;
                    result = '';
                    for (i = 0; i < node.childNodes.length; i++) {
                        child = node.childNodes[i];
                        text = null;
                        if (child.nodeType === 1 && child.tagName !== "script") {
                            text = getTextFromNode(child, addSpaces);
                        } else if (child.nodeType === 3) {
                            text = child.nodeValue;
                        }
                        if (text) {
                            if (addSpaces && /\S$/.test(result) && /^\S/.test(text))
                                text = ' ' + text;
                            result += text;
                        }
                    }
                    return result;
                }
                ;

                var parseText = function (response) {

                    var tmp = document.implementation.createHTMLDocument();
                    tmp.body.innerHTML = response;

                    var body = tmp.body;
                    var txt2 = tmp.getElementsByTagName("p");
                    
                    var test = "";
                    for (var i = 0; i < txt2.length; i++) {
                        test += getTextFromNode(txt2[i]);
                    }                    

                    return test;
                };
                return parseText(content);
            },
            getVideos: function (content) {

                var parseVideos = function (response) {

                    var tmp = document.implementation.createHTMLDocument();
                    tmp.body.innerHTML = response;

                    var videos1 = tmp.getElementsByTagName("video");
                    var videos2 = tmp.getElementsByTagName("object");
                    
                    for (var i = 0; i < videos2.length; i++) {
                        if((videos2[i].id).search("player") === -1){
                            videos2.splice(i, i+1);
                        }
                    }   
                    
                    var videos = angular.extend([], videos1, videos2);

                    return videos;
                };
                return parseVideos(content);

            },
            getAudio: function (content) {

                var parseAudio = function (response) {

                    var tmp = document.implementation.createHTMLDocument();
                    tmp.body.innerHTML = response;

                    var audio = tmp.getElementsByTagName("audio");

                    return audio;
                };
                return parseAudio(content);
            }
        };
    };

    var ScrapeController = function ($scope, scraperService) {
        var scraper = this;
        scraper.imageArray = [];
        scraper.text = "";
        scraper.videosArray = [];
        scraper.audioArray = [];
        scraper.url = "";

        $scope.$watch('address', function (val) {
            if (val) {
                scraper.url = val;
            }
        });

        $scope.doScrape = function () {
            console.log("scrape start!");
            function createCORSRequest(method, url) {
                var xhr = new XMLHttpRequest();
                if ("withCredentials" in xhr) {
                    // XHR for Chrome/Firefox/Opera/Safari.
                    xhr.open(method, url, true);
                } else if (typeof XDomainRequest !== "undefined") {
                    // XDomainRequest for IE.
                    xhr = new XDomainRequest();
                    xhr.open(method, url);
                } else {
                    // CORS not supported.
                    xhr = null;
                }
                return xhr;
            }
            function makeCorsRequest(url) {
                // All HTML5 Rocks properties support CORS.
                var xhr = createCORSRequest('GET', url);
                if (!xhr) {
                    alert('CORS not supported');
                    return;
                }

                // Response handlers.
                xhr.onload = function () {
                    //alert('Response from CORS request to ' + url);
                    scraper.imageArray = scraperService.getImages(xhr.responseText);

                    scraper.text = scraperService.getText(xhr.responseText);

                    scraper.videosArray = scraperService.getVideos(xhr.responseText);
                    console.log(scraper.videosArray);

                    scraper.audioArray = scraperService.getAudio(xhr.responseText);

                    $scope.$apply();
                };

                xhr.onerror = function () {
                    alert('Woops, there was an error making the request.');
                };

                xhr.send();
            }
            makeCorsRequest("https://crossorigin.me/" + scraper.url);

        };

    };

    var myApp = angular.module('webOrganizer', []);

    myApp.config(function ($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    });

    myApp.factory('scraperService', ['$http', scraperService]);
    myApp.controller('ScrapeController', ['$scope', 'scraperService', ScrapeController]);
})();