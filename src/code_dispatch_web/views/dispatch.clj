(ns
  code-dispatch-web.views.dispatch
  (:require
   [clojure.string :as string]
   [hiccup.core :refer [h]]
   [hiccup.form
    :refer
    [text-field password-field form-to submit-button text-area]]
   [hiccup.page :refer [include-css include-js html5]]
   [hiccup.element :refer [unordered-list link-to]]
   [ring.util.codec :refer [url-encode]]
   [code-dispatch-web.views.common :as common]
   [clojure.pprint :as pprint]
   [rewrite-clj.parser :as p]
   [rewrite-clj.printer :as prn]))
 (defn
  do-show
  []
  (common/main-layout
   [:div.content
    [:h2 "Submit code"]
    (form-to
     [:post "/"]
     [:fieldset
      [:div.clearfix
       (text-area
        {:rows 30, :class "input-block-level"}
        "code"
        "(defn str-set [instance field-str arg]    (clojure.lang.Reflector/setInstanceField        instance     field-str         arg))")
       [:br]
       (submit-button {:class "submit"} "Indent")]])]))
 (defn
  do-format
  [params]
  (common/main-layout
   [:div.content
    [:h2 "Submit code"]
    (form-to
     [:post "/"]
     [:fieldset
      [:div.clearfix
       (let
        [fcode
         (with-out-str
          (clojure.pprint/write
            (read-string (str "'" (params :code) ""))))]
        (text-area
         {:rows 30, :class "input-block-level"}
         "code"
         ;;(subs fcode 6 (- (count fcode) 1))
         (subs fcode 1)
         ))
       [:br]
       (submit-button {:class "submit"} "Indent")]])]))