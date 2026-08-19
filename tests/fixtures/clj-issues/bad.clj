(ns example.bad)

(defn unused-binding []
  (let [x 1]
    "no x used"))
