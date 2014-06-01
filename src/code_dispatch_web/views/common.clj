(ns code-dispatch-web.views.common
  
  (:require [clojure.string :as string]
            [hiccup.core :refer [h]]
            [hiccup.page :refer [include-css include-js html5]]
            [hiccup.element :refer [unordered-list link-to]]
            [ring.util.codec :refer [url-encode]]
            )
  )




;; Links and includes

(def includes {
               :default (include-css "/css/default.css")
               :reset (include-css "/css/reset.css")
               :bootstrap (include-css "/bootstrap/css/bootstrap.css")
               :bootstrap-responsive (include-css "/bootstrap/css/bootstrap-responsive.css")
               :favicon [:link {:rel "shortcut icon" :href "/bootstrap/ico/favicon.ico"}]
               :touch144 [:link {:rel "apple-touch-icon-precomposed" :sizes "144x144" :href "/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png"}]
               :touch114 [:link {:rel "apple-touch-icon-precomposed" :sizes "114x114" :href "/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png"}]
               :touch72 [:link {:rel "apple-touch-icon-precomposed" :sizes "72x72" :href "/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png"}]
               :touch57 [:link {:rel "apple-touch-icon-precomposed" :href "/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png"}]
               })

(def scripts {
              :jquery (include-js "/bootstrap/js/jquery.js")
              :prettify (include-js "/bootstrap/js/google-code-prettify/prettify.js")
              :bootstrap-transition (include-js "/bootstrap/js/bootstrap-transition.js")
              :bootstrap-alert (include-js "/bootstrap/js/bootstrap-alert.js")
              :bootstrap-modal (include-js "/bootstrap/js/bootstrap-modal.js")
              :bootstrap-dropdown (include-js "/bootstrap/js/bootstrap-dropdown.js")
              :bootstrap-scrollspy (include-js "/bootstrap/js/bootstrap-scrollspy.js")
              :bootstrap-tab (include-js "/bootstrap/js/bootstrap-tab.js")
              :bootstrap-tooltip (include-js "/bootstrap/js/bootstrap-tooltip.js")
              :bootstrap-popover (include-js "/bootstrap/js/bootstrap-popover.js")
              :bootstrap-button (include-js "/bootstrap/js/bootstrap-button.js")
              :bootstrap-collapse (include-js "/bootstrap/js/bootstrap-collapse.js")
              :bootstrap-carousel (include-js "/bootstrap/js/bootstrap-carousel.js")
              :bootstrap-typeahead (include-js "/bootstrap/js/bootstrap-typeahead.js")
              :bookshop.js (include-js "/js/bookshop.js")
              })

(defn build-head [incls]
  [:head
   [:meta {:charset "utf-8"}]
   [:title "Bookie Bookshop"]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
   [:meta {:name "description" :content "Lab web site for trying out Datomic as storage engine"}]
   [:meta {:name "author" :content "Ulrik Sandberg, Jayway"}]
   (map #(get includes %) incls)
   ]
  )

(defn build-scripts [scrpts]
  (map #(get scripts %) scrpts))

;; Layouts

(defn main-layout [ & content]
  (html5
    (build-head [:bootstrap :style :bootstrap-responsive
                 :favicon
                 :touch144 :touch114 :touch72 :touch57
                 :jquery
                 :bookshop.js
                 ])
    [:body {:data-spy "scroll" :data-target ".subnav" :data-offset "50"}

    
     ;; content

     [:div.container content]

     ;; footer

     [:footer
      [:div.container
       [:div.row
        [:div.span1
         (link-to {:id "about"} "/about" "About cdw")]
        [:div.span2
         "Copyright © " [:a {:href "http://www.otbc.se"} "OTBC"]]]
       [:div.row
        [:div.span12
         "Powered by " [:a {:href "http://clojure.org"} "Clojure"]
         ", " (link-to "http://github.com/mmcgrana/ring" "Ring")
         ", " (link-to "http://github.com/weavejester/compojure/" "Compojure")
         ", " (link-to "http://twitter.github.com/bootstrap/" "Twitter Bootstrap")
         ]]]]

     ;; scripts loaded last, for page load speed

     (build-scripts [:jquery
                     :prettify
                     :bootstrap-transition
                     :bootstrap-alert
                     :bootstrap-modal
                     :bootstrap-dropdown
                     :bootstrap-scrollspy
                     :bootstrap-tab
                     :bootstrap-tooltip
                     :bootstrap-popover
                     :bootstrap-button
                     :bootstrap-collapse
                     :bootstrap-carousel
                     :bootstrap-typeahead
                     :bookshop.js])
 
]))
